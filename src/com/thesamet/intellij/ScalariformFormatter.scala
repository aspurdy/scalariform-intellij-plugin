package com.thesamet.intellij

import com.intellij.notification.{NotificationType, Notifications, Notification}
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.annotations.NotNull

import scala.annotation.tailrec
import scalariform.formatter.ScalaFormatter
import scalariform.formatter.preferences._
import scalariform.formatter.preferences.AlignSingleLineCaseStatements.MaxArrowIndent
import scalariform.parser.ScalaParserException

object ScalariformFormatter {
  def getInstance(@NotNull project: Project): ScalariformFormatter =
    project.getComponent(classOf[ScalariformFormatter])
}

class ScalariformFormatter(project: Project) extends ProjectComponent {

  override def getComponentName: String = getClass.getSimpleName

  override def projectOpened(): Unit = ()

  override def projectClosed(): Unit = ()

  override def initComponent(): Unit = ()

  override def disposeComponent(): Unit = ()

  def formattingPreferences: FormattingPreferences = {
    val settings = ScalariformSettings.getInstance(project)
    FormattingPreferences
      .setPreference(RewriteArrowSymbols, settings.isRewriteArrowSymbols)
      .setPreference(IndentSpaces, settings.getIndentSpaces.toInt)
      .setPreference(SpaceBeforeColon, settings.isSpaceBeforeColon)
      .setPreference(CompactStringConcatenation, settings.isCompactStringConcatenation)
      .setPreference(PreserveSpaceBeforeArguments, settings.isPreserveSpaceBeforeArguments)
      .setPreference(AlignParameters, settings.isAlignParameters)
      .setPreference(AlignArguments, settings.isAlignArguments)
      .setPreference(DoubleIndentClassDeclaration, settings.isDoubleIndentClassDeclaration)
      .setPreference(FormatXml, settings.isFormatXML)
      .setPreference(IndentPackageBlocks, settings.isIndentPackageBlocks)
      .setPreference(AlignSingleLineCaseStatements, settings.isAlignSingleLineCase)
      .setPreference(MaxArrowIndent, settings.getAlignSingleLineCaseStatementsMaxArrowIndent.toInt)
      .setPreference(IndentLocalDefs, settings.isIndentLocalDefs)
      .setPreference(SpaceInsideParentheses, settings.isSpaceInsideParenthesis)
      .setPreference(SpaceInsideBrackets, settings.isSpaceInsideBrackets)
      .setPreference(SpacesWithinPatternBinders, settings.isSpacesWithinPatternBinders)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, settings.isMultilineScalaDocCommentsStartOnFirstLine)
      .setPreference(IndentWithTabs, settings.isIndentWithTabs)
      .setPreference(CompactControlReadability, settings.isCompactControlReadability)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, settings.isPlaceScalaDocAsteriskBeneathSecondAsterisk)
      .setPreference(SpacesAroundMultiImports, settings.isSpacesAroundMultiImports)
  }

  def format(vfile: VirtualFile): Unit = {
    lazy val fileDocManager = FileDocumentManager.getInstance
    lazy val prefs = formattingPreferences
    @tailrec
    def formatInternal(vfiles: List[VirtualFile]): Unit = vfiles match {
      case f :: tail if Seq("Scala", "SBT").contains(f.getFileType.getName) =>
        try {
          val document = fileDocManager.getDocument(f)
          val source = document.getText
          val formatted = ScalaFormatter.format(source, formattingPreferences = prefs)
          if (source != formatted) {
            ApplicationManager.getApplication.runWriteAction(new Runnable {
              override def run(): Unit = document.setText(formatted)
            })
          }
        } catch {
          case e: ScalaParserException => Notifications.Bus.notify(new Notification(
            "scalariform", "Scalariform - " + f.getPath, e.getMessage, NotificationType.INFORMATION
          ))
        }
        formatInternal(tail)

      case f :: tail if f.isDirectory => formatInternal(f.getChildren.toList ::: tail)

      case f :: tail                  => formatInternal(tail)

      case _                          => //ignored
    }
    formatInternal(List(vfile))
  }
}

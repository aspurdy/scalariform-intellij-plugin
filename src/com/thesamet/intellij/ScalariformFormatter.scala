package com.thesamet.intellij

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.vfs.VirtualFile

import scalariform.formatter.ScalaFormatter
import scalariform.formatter.preferences.AlignSingleLineCaseStatements.MaxArrowIndent
import scalariform.formatter.preferences._

object ScalariformFormatter {

  case class FileDocument(file: VirtualFile, document: Document) {
    def isScala: Boolean = file.getFileType.getName == "Scala"
  }

  def format(optFileDoc: Option[FileDocument]) = {
    lazy val pref = formattingPreferences
    optFileDoc.filter(_.isScala)
      .foreach {
        fileDoc =>
          val source = fileDoc.document.getText()
          val formatted = ScalaFormatter.format(source, formattingPreferences = pref)
          if (source != formatted) {
            ApplicationManager.getApplication.runWriteAction(new Runnable {
              override def run(): Unit = {
                fileDoc.document.setText(formatted)
              }
            })
          }
      }

  }

  def formattingPreferences: FormattingPreferences = {
    val component: ScalariformApplicationComponent =
      ApplicationManager.getApplication.getComponent(classOf[ScalariformApplicationComponent])

    FormattingPreferences.setPreference(RewriteArrowSymbols, component.isRewriteArrowSymbols)
      .setPreference(IndentSpaces, component.getIndentSpaces.toInt)
      .setPreference(SpaceBeforeColon, component.isSpaceBeforeColon)
      .setPreference(CompactStringConcatenation, component.isCompactStringConcatenation)
      .setPreference(PreserveSpaceBeforeArguments, component.isPreserveSpaceBeforeArguments)
      .setPreference(AlignParameters, component.isAlignParameters)
      .setPreference(AlignArguments, component.isAlignArguments)
      .setPreference(DoubleIndentClassDeclaration, component.isDoubleIndentClassDeclaration)
      .setPreference(FormatXml, component.isFormatXML)
      .setPreference(IndentPackageBlocks, component.isIndentPackageBlocks)
      .setPreference(AlignSingleLineCaseStatements, component.isAlignSingleLineCase)
      .setPreference(MaxArrowIndent, component.getAlignSingleLineCaseStatementsMaxArrowIndent.toInt)
      .setPreference(IndentLocalDefs, component.isIndentLocalDefs)
      .setPreference(SpaceInsideParentheses, component.isSpaceInsideParenthesis)
      .setPreference(SpaceInsideBrackets, component.isSpaceInsideBrackets)
      .setPreference(SpacesWithinPatternBinders, component.isSpacesWithinPatternBinders)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, component.isMultilineScalaDocCommentsStartOnFirstLine)
      .setPreference(IndentWithTabs, component.isIndentWithTabs)
      .setPreference(CompactControlReadability, component.isCompactControlReadability)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, component.isPlaceScalaDocAsteriskBeneathSecondAsterisk)
      .setPreference(SpacesAroundMultiImports, component.isSpacesAroundMultiImports)
  }

}

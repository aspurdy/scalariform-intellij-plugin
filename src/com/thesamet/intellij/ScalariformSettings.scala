package com.thesamet.intellij

import java.io.File

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components._
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull

import scala.beans.{BooleanBeanProperty, BeanProperty}

object ScalariformSettings {
  def getInstance(@NotNull project: Project): ScalariformSettings =
    project.getComponent(classOf[ScalariformSettings])
}

@State(
  name     = "ScalariformSettings",
  storages = Array(
    new Storage(StoragePathMacros.WORKSPACE_FILE),
    new Storage("scalariform_settings.xml")
  )
)
class ScalariformSettings extends PersistentStateComponent[ScalariformSettings] with ExportableComponent {

  override def getState: ScalariformSettings = this

  override def loadState(state: ScalariformSettings): Unit = XmlSerializerUtil.copyBean(state, this)

  @NotNull
  override def getExportFiles: Array[File] = Array(PathManager.getOptionsFile("scalariform_settings"))

  @NotNull
  override def getPresentableName: String = "Scalariform Settings"

  @BooleanBeanProperty var alignParameters: Boolean = true
  @BooleanBeanProperty var alignArguments: Boolean = true
  @BooleanBeanProperty var alignSingleLineCase: Boolean = true
  @BooleanBeanProperty var compactControlReadability: Boolean = false
  @BooleanBeanProperty var compactStringConcatenation: Boolean = false
  @BooleanBeanProperty var doubleIndentClassDeclaration: Boolean = false
  @BooleanBeanProperty var formatXML: Boolean = true
  @BooleanBeanProperty var indentPackageBlocks: Boolean = false
  @BooleanBeanProperty var indentWithTabs: Boolean = false
  @BooleanBeanProperty var multilineScalaDocCommentsStartOnFirstLine: Boolean = false
  @BooleanBeanProperty var placeScalaDocAsteriskBeneathSecondAsterisk: Boolean = false
  @BooleanBeanProperty var preserveSpaceBeforeArguments: Boolean = false
  @BooleanBeanProperty var rewriteArrowSymbols: Boolean = false
  @BooleanBeanProperty var spaceBeforeColon: Boolean = false
  @BooleanBeanProperty var spaceInsideParenthesis: Boolean = false
  @BooleanBeanProperty var spacesWithinPatternBinders: Boolean = true
  @BeanProperty var alignSingleLineCaseStatementsMaxArrowIndent: Integer = 30
  @BeanProperty var indentSpaces: Integer = 2
  @BooleanBeanProperty var indentLocalDefs: Boolean = false
  @BooleanBeanProperty var spaceInsideBrackets: Boolean = false
  @BooleanBeanProperty var spacesAroundMultiImports: Boolean = false
  @BooleanBeanProperty var autoFormatOnSave: Boolean = false
}

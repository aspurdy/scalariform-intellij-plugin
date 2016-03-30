package com.thesamet.intellij

import javax.swing._

import com.intellij.openapi.options.{Configurable, ConfigurationException}
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.{Nls, Nullable}

class ScalariformSettingsConfigurable(project: Project) extends Configurable {
  private var myPanel = new ScalariformSettingsPanel(project)
  private var myComponent: JComponent = myPanel.getPanel

  @Nls
  override def getDisplayName: String = "Scalariform"

  @Nullable
  override def getHelpTopic: String = null

  @Nullable
  override def createComponent: JComponent = myComponent

  override def isModified: Boolean = myPanel.isModified

  @throws[ConfigurationException]
  override def apply(): Unit = myPanel.apply()

  override def reset(): Unit = myPanel.reset()

  override def disposeUIResources(): Unit = {
    myPanel = null
    myComponent = null
  }
}

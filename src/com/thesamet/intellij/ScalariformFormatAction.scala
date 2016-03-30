package com.thesamet.intellij

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, CommonDataKeys}
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.fileEditor.{FileEditorManager, FileDocumentManager}
import com.intellij.openapi.project.Project

class ScalariformFormatAction extends AnAction {
  import com.thesamet.intellij.ScalariformFormatter._

  override def update(event: AnActionEvent): Unit = {
    event.getPresentation.setEnabled(getCurrentFileDocument(event).exists(_.isScala))
  }

  override def actionPerformed(event: AnActionEvent): Unit = {
    val formatter = ScalariformFormatter.getInstance(getCurrentProject(event))
    CommandProcessor.getInstance().runUndoTransparentAction(new Runnable() {
      override def run(): Unit = formatter.format(getCurrentFileDocument(event))
    })
  }

  private def getCurrentProject(event: AnActionEvent): Project = event.getData(CommonDataKeys.PROJECT)

  private def getCurrentFileDocument(event: AnActionEvent): Option[FileDocument] = for {
    project <- Option(getCurrentProject(event))
    editor <- Option(FileEditorManager.getInstance(project).getSelectedTextEditor)
    document <- Option(editor.getDocument)
    vfile <- Option(FileDocumentManager.getInstance().getFile(document))
  } yield FileDocument(vfile, document)

}

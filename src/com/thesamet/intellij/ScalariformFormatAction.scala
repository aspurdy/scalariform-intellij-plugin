package com.thesamet.intellij

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, CommonDataKeys}
import com.intellij.openapi.fileEditor.{FileDocumentManager, FileEditorManager}

class ScalariformFormatAction extends AnAction {
  import com.thesamet.intellij.ScalariformFormatter._

  override def update(event: AnActionEvent): Unit = {
    event.getPresentation.setEnabled(getCurrentFileDocument(event).exists(_.isScala))
  }

  override def actionPerformed(event: AnActionEvent) {
    format(getCurrentFileDocument(event))
  }

  private def getCurrentFileDocument(event: AnActionEvent): Option[FileDocument] = for {
    project <- Option(event.getData(CommonDataKeys.PROJECT))
    editor <- Option(FileEditorManager.getInstance(project).getSelectedTextEditor)
    document <- Option(editor.getDocument)
    vfile <- Option(FileDocumentManager.getInstance().getFile(document))
  } yield FileDocument(vfile, document)

}

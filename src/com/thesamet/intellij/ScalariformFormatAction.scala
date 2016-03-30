package com.thesamet.intellij

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, CommonDataKeys}
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.vfs.VirtualFile

class ScalariformFormatAction extends AnAction {

  override def update(event: AnActionEvent): Unit =
    event.getPresentation.setEnabled(getSelectedFiles(event).nonEmpty)

  override def actionPerformed(event: AnActionEvent): Unit = {
    val formatter = ScalariformFormatter.getInstance(event.getProject)
    CommandProcessor.getInstance().runUndoTransparentAction(new Runnable() {
      override def run(): Unit = getSelectedFiles(event).foreach(formatter.format)
    })
  }

  private def getSelectedFiles(event: AnActionEvent): Seq[VirtualFile] =
    Option(event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)).toList.flatten
}

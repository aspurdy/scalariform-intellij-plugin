package com.thesamet.intellij

import com.intellij.AppTopics
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.{FileDocumentManagerAdapter, FileDocumentManager}
import com.intellij.openapi.project.Project
import com.intellij.util.messages.MessageBusConnection

class FormatOnSave(project: Project) extends ProjectComponent {
  import com.thesamet.intellij.ScalariformFormatter._

  private var connection: MessageBusConnection = null

  override def getComponentName: String = getClass.getSimpleName

  override def projectOpened(): Unit = {
    connection = project.getMessageBus.connect()
    connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new DocumentFormatter)
  }

  override def projectClosed(): Unit = connection.disconnect()

  override def initComponent(): Unit = ()

  override def disposeComponent(): Unit = ()

  class DocumentFormatter extends FileDocumentManagerAdapter {
    override def beforeDocumentSaving(document: Document) = {
      val formatter = ScalariformFormatter.getInstance(project)
      val settings = ScalariformSettings.getInstance(project)
      if (settings.isAutoFormatOnSave) {
        CommandProcessor.getInstance().runUndoTransparentAction(new Runnable() {
          override def run(): Unit = formatter.format(getCurrentFileDocument(document))
        })
      }
    }

    private def getCurrentFileDocument(document: Document): Option[FileDocument] = for {
      vfile <- Option(FileDocumentManager.getInstance().getFile(document))
    } yield FileDocument(vfile, document)
  }
}
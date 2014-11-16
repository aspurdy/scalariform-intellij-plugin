package com.thesamet.intellij

import com.intellij.AppTopics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.{FileDocumentManager, FileDocumentManagerAdapter}
import com.intellij.util.messages.MessageBusConnection

class FormatOnSave extends ApplicationComponent {
  import com.thesamet.intellij.ScalariformFormatter._

  private var connection: MessageBusConnection = null

  class DocumentFormatter extends FileDocumentManagerAdapter {
    override def beforeDocumentSaving(document: Document) = {
      val component: ScalariformApplicationComponent =
        ApplicationManager.getApplication.getComponent(classOf[ScalariformApplicationComponent])
      if (component.isAutoFormatOnSave) {
        CommandProcessor.getInstance().runUndoTransparentAction(new Runnable() {
          override def run() {
            format(getCurrentFileDocument(document))
          }
        })
      }
    }

    private def getCurrentFileDocument(document: Document): Option[FileDocument] = for {
      vfile <- Option(FileDocumentManager.getInstance().getFile(document))
    } yield FileDocument(vfile, document)

  }

  def initComponent {
    connection = ApplicationManager.getApplication.getMessageBus.connect
    connection.subscribe(AppTopics.FILE_DOCUMENT_SYNC, new DocumentFormatter)
  }

  def disposeComponent {
    connection.disconnect
  }

  def getComponentName: String = {
    getClass.getSimpleName
  }
}
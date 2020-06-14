package com.example.printpdf

import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createPdf(view: View) {

        Toast.makeText(this, "create Pdf Clicked", Toast.LENGTH_SHORT).show()

        val pdfDocument = PdfDocument()
        val myPaint = Paint()


        //Page 1
        val myPageInfo1 = makePageInfo(250, 500, 1)
        val myPage1 = pdfDocument.startPage(myPageInfo1)
        makePage(pdfDocument, myPage1, myPaint, "This is Page 1")

        //Page 2
        val myPageInfo2 = makePageInfo(250, 500, 2)
        val myPage2 = pdfDocument.startPage(myPageInfo2)
        makePage(pdfDocument, myPage2, myPaint, "This is Page 2")

        //Page 3
        val myPageInfo3 = makePageInfo(250, 500, 3)
        val myPage3 = pdfDocument.startPage(myPageInfo3)
        makePage(pdfDocument, myPage3, myPaint, "This is Page 3")

        //Saving the Pdf in package
        val file = File(this.getExternalFilesDir(null), "/FirstPdf.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(this, "pdf is created", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

        //Pdf Document is closed
        pdfDocument.close()
    }


    private fun makePageInfo(width: Int, height: Int, pageNumber: Int): PdfDocument.PageInfo? {

        return PdfDocument.PageInfo.Builder(width, height, pageNumber).create()
    }

    private fun makePage(
        pdfDocument: PdfDocument,
        myPage: PdfDocument.Page?,
        myPaint: Paint,
        message: String?
    ) {

        val canvas = myPage?.canvas
        canvas?.drawText(message!!, 20f, 20f, myPaint)
        pdfDocument.finishPage(myPage)

    }


}
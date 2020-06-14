package com.example.printpdf

import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    val informationArray: Array<String> =
        arrayOf("Name", "Company Name", "Address", "Phone", "Email")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createPdf(view: View) {

        Toast.makeText(this, "create Pdf Clicked", Toast.LENGTH_SHORT).show()

        val pdfDocument = PdfDocument()
        val myPaint = Paint()

        makeForm(pdfDocument, myPaint)


/*
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
        pdfDocument.close()*/
    }

    private fun makeForm(pdfDocument: PdfDocument, myPaint: Paint) {

        val pageInfo1 = makePageInfo(250, 400, 1)
        val page1 = pdfDocument.startPage(pageInfo1)

        val canvas = page1.canvas
        myPaint.textAlign = Paint.Align.CENTER
        myPaint.textSize = 12.0f
        canvas.drawText("RK Enterprises", pageInfo1?.pageWidth?.div(2)?.toFloat()!!, 30f, myPaint)

        myPaint.textSize = 6.0f
        myPaint.color = Color.rgb(122, 119, 119)
        canvas.drawText(
            "Vidyanagar west near NamanVidya School, Harmu Ranchi",
            pageInfo1.pageWidth.div(2).toFloat(),
            40f,
            myPaint
        )

        myPaint.textAlign = Paint.Align.LEFT
        myPaint.textSize = 9.0f
        myPaint.color = Color.rgb(122, 119, 119)
        canvas.drawText("Customer Information", 10f, 70f, myPaint)

        myPaint.textAlign = Paint.Align.LEFT
        myPaint.textSize = 8.0f
        myPaint.color = Color.BLACK

        val startXPosition = 10.0f
        val endXPosition = (pageInfo1.pageWidth - 10.0).toFloat()
        var startYPosition = 100.0f

        for (i: Int in informationArray.indices) {

            canvas.drawText(informationArray[i], startXPosition, startYPosition - 2, myPaint)

            canvas.drawLine(
                startXPosition,
                startYPosition,
                endXPosition,
                startYPosition + 3,
                myPaint
            )

            startYPosition += 20.0f

        }

        canvas.drawLine(80.0f, 92.0f, 80.0f, 190.0f, myPaint)

        myPaint.style = Paint.Style.STROKE
        myPaint.strokeWidth = 2.0f

        canvas.drawRect(10.0f, 200.0f, (pageInfo1.pageWidth - 10).toFloat(), 300.0f, myPaint)

        canvas.drawLine(85.0f, 200.0f, 85.0f, 300.0f, myPaint)
        canvas.drawLine(163.0f, 200.0f, 163.0f, 300.0f, myPaint)

        myPaint.strokeWidth = 0.0f
        myPaint.style = Paint.Style.FILL

        canvas.drawText("Photo", 35.0f, 250.0f, myPaint)
        canvas.drawText("Photo", 110.0f, 250.0f, myPaint)
        canvas.drawText("Photo", 185.0f, 250.0f, myPaint)

        canvas.drawText("Note", 10.0f, 320.0f, myPaint)
        canvas.drawLine(35.0f, 325.0f, (pageInfo1.pageWidth - 10).toFloat(), 325.0f, myPaint)
        canvas.drawLine(10.0f, 345.0f, (pageInfo1.pageWidth - 10).toFloat(), 345.0f, myPaint)
        canvas.drawLine(10.0f, 365.0f, (pageInfo1.pageWidth - 10).toFloat(), 365.0f, myPaint)

        pdfDocument.finishPage(page1)

        saveFile(pdfDocument, "Customer Information")

    }

    private fun saveFile(pdfDocument: PdfDocument, fileName: String) {

        //Saving the Pdf in package
        val file = File(this.getExternalFilesDir(null), "/$fileName.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(this, "pdf is created", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        pdfDocument.close()

    }


    private fun makePageInfo(width: Int, height: Int, pageNumber: Int): PdfDocument.PageInfo? {

        return PdfDocument.PageInfo.Builder(width, height, pageNumber).create()
    }

    /* private fun makePage(
         pdfDocument: PdfDocument,
         myPage: PdfDocument.Page?,
         myPaint: Paint,
         message: String?
     ) {

         val canvas = myPage?.canvas
         canvas?.drawText(message!!, 20f, 20f, myPaint)
         pdfDocument.finishPage(myPage)

     }
 */

}
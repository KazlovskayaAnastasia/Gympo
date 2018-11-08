package com.gmail.martsulgp.gympo.extras

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.gmail.martsulgp.gympo.R
import java.util.*


@TargetApi(Build.VERSION_CODES.N)
class TimerView : View {

    internal var date = Date()
    internal var calendar: Calendar = GregorianCalendar()
    internal var minutes: Int = 0
    internal var hours: Int = 0
    internal var radius: Float = 0.toFloat()
    internal var dotRadius: Float = 0.toFloat()
    private val thread: Thread? = null

    private val myPan = Paint()

    constructor(context: Context) : super(context) {

        initialize()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize()

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize()
    }

    private fun initialize() {
        myPan.isAntiAlias = true
        myPan.style = Paint.Style.STROKE

        val r = resources
        val strokeWidthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4f, r.displayMetrics)
        myPan.strokeWidth = strokeWidthInPx


        seconds = 0
        //        seconds = calendar.getTime().getSeconds();
        minutes = calendar.time.minutes
        hours = calendar.time.hours
        radius = (width / 3).toFloat()
        dotRadius = 6f

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //Log.println(Log.INFO,"AAAAA",calendar.getTime().toString());

        myPan.textSize = 24f
        myPan.color = ContextCompat.getColor(context, R.color.red)
        myPan.style = Paint.Style.FILL
        canvas.drawText(calendar.time.toString(), (-210 + width / 2).toFloat(), (5 * height / 6).toFloat(), myPan)


        myPan.color = ContextCompat.getColor(context, R.color.white)
        for (i in 0..59) {
            canvas.drawCircle((width / 2).toFloat(), (height / 2 - width / 3).toFloat(), dotRadius, myPan)
            canvas.rotate(6f, (width / 2).toFloat(), (height / 2).toFloat())
        }

        myPan.color = ContextCompat.getColor(context, R.color.green)
        canvas.drawCircle((width / 2).toFloat(), (height / 2 - width / 3).toFloat(), 2 * dotRadius, myPan)

        myPan.color = ContextCompat.getColor(context, R.color.red)

        val count = canvas.save()
        for (i in 0..seconds) {
            if (i != 0) {
                //myPan.setStrokeWidth(10);
                canvas.drawCircle((width / 2).toFloat(), (height / 2 - width / 3).toFloat(), dotRadius, myPan)
            }
            canvas.rotate(6f, (width / 2).toFloat(), (height / 2).toFloat())
        }

        canvas.restoreToCount(count)
        canvas.rotate(180f, (width / 2).toFloat(), (height / 2).toFloat())
        //        for(int i=0;i<60;i++){
        //            if(i==minutes) {
        //                myPan.setStrokeWidth(10);
        //                canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2,(getHeight() / 2 - radius + 290), myPan);
        //            }
        //
        //            if(i==seconds) {
        //                myPan.setStrokeWidth(5);
        //                canvas.drawLine(getWidth() / 2, (getHeight() / 2 - radius + 310), getWidth() / 2,getHeight() / 2, myPan);
        //            }
        //            canvas.rotate(6,getWidth()/2,getHeight()/2);
        //        }
        //        for(int i=0;i<=hours;i++){
        //            if(i==hours) {
        //                myPan.setStrokeWidth(14);
        //                canvas.drawLine(getWidth() / 2, (getHeight() / 2 - radius + 250), getWidth() / 2,getHeight() / 2, myPan);
        //            }
        //            canvas.rotate(30,getWidth()/2,getHeight()/2);
        //
        //        }
        invalidate()
    }

    companion object {

        var seconds: Int = 0
    }


}

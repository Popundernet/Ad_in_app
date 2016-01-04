# Adview

To add adview banner to your project

#In porject

Add this source code like library project

#In activity_main.xml

     <?xml version="1.0" encoding="utf-8"?>
     <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      xmlns:custom="http://schemas.android.com/apk/res-auto"
      tools:context="com.advertise.simpleproj.MainActivity">

      <com.advertise.adview.AdView
        android:id="@+id/advertise"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        custom:api="YOUR_API_KEY"   <!-- Your advertise api key -->
        custom:size="bottomline"        <!-- Banner size: "fullscreen" ; "bottomline" ; "topline" -->
        />
       </RelativeLayout>


#In Manifest.xml 

     <uses-permission android:name="android.permission.INTERNET"/>

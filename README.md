# adview 

 To add ADView in your code:

# In project 
 - download this source code and add it like library project.

# In activity_main.xml 

     <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      xmlns:custom="http://schemas.android.com/apk/res-auto"
      tools:context="com.advertise.simpleproj.MainActivit>
            <com.advertise.adview.AdView
               android:id="@+id/advertise"
                custom:api="----YOUR_API_KEY---"  <!-- Insert your key here-->
                android:layout_width="match_parent"
                 android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
        />
      </RelativeLayout>


# In manifest.xml 



    <uses-permission android:name="android.permission.INTERNET"/>  



[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Indicators-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1401)

# Android Indicators

A simple library to add some effect to screen title.

## Available Effects
1. Default (Left Right Transition Effect)
2. UpDown (Up Down Transition Effect)
3. 3D (Rotation on Y Axis)

## Usage
1- From XML
```
    <com.moshx.indicators.title.TitleIndicator
        android:textAppearance="?android:attr/textAppearanceMedium"
        android:id="@+id/titleIndicator"
        android:textColor="@android:color/black"
        android:layout_width="300dp"
        android:layout_height="wrap_content" />
```
2- From Code
```
    TitleIndicator indicator=new TitleIndicator(this);
    observer.addObservableView(indicator);
    indicator.setToolBar(toolbar);
```

## Supported View Attributes
1. All FrameLayout attributes
2. All TextView attributes

## Requirements 
1. Min SDK: 15 (Android 4.0.3–4.0.4 Ice Cream Sandwich)
2. Android support library v7
3. Android SDK 21
4. Build Tool 21.1.2
5. Java 1.7 compilation

## Add to project
* Using gradle
```
dependencies {
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile 'com.moshx:androidindicators:0.6.0@arr'
}
```

* Clone the project and add it as module
```
   compile project(':library')
```


## Demo APK (Unsigned)
you can download demo apk from [releases](https://github.com/MoshDev/AndroidIndicators/releases) or [**from here**](https://github.com/MoshDev/AndroidIndicators/releases/download/demo/app-debug.apk)

## Snapshot (gif)
![snapshot](https://raw.githubusercontent.com/MoshDev/AndroidIndicators/master/snapshots/2015-01-12%2019_42_02.gif)

[or check this youtube video](http://www.youtube.com/watch?v=UR9ae9WRpBI)


## IconicTabsView [preview]
[Check this Video](http://www.youtube.com/watch?v=706a5B_ql3g)




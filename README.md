# Android Indicators

A simple library to add some effect to screen title.

## Available Effects
1. Default (Left Right Transition Effect)
2. UpDown (Up Down Transition Effect)
3. 3D (Rotation on Y Axis)

## Usage
1. From XML
```
    <com.moshx.indicators.title.TitleIndicator
        android:textAppearance="?android:attr/textAppearanceMedium"
        android:id="@+id/titleIndicator"
        android:textColor="@android:color/black"
        android:layout_width="300dp"
        android:layout_height="wrap_content" />
```
2. From Code
```
    TitleIndicator indicator=new TitleIndicator(this);
    observer.addObservableView(indicator);
    indicator.setToolBar(toolbar);
```

## Supported View Attributes
1. All FrameLayout attributes
2. All TextView attributes

## Requirements 
1. Android support library v7
2. Android SDK 21
3. Build Tool 21.1.2
4. Java 1.7 compilation

## Add to project
* Using gradle
*not available yet, sonatype didn't verify the repository yet*
```
dependencies {
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile 'com.moshx:AndroidIndicators:0.5.0'
}
```

* Clone the project and add it as module
```
   compile project(':library')
```


###Currently in alpha (not tested enough)

## Snapshot (gif)
![snapshot](https://raw.githubusercontent.com/MoshDev/AndroidIndicators/master/snapshots/2015-01-12%2019_42_02.gif)


[or check this youtube video](http://www.youtube.com/watch?v=UR9ae9WRpBI)


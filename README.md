# NYTimes

Build a simple app to fetch the NY Times Articles API and show a list of articles, that shows details when
items on the list are tapped (a typical master/detail app).


# Technologies

* Retrofit
* Coroutine
* MVVM
* LiveData
* RecyclerView
* DataBinding
* PreferenceScreen


# Code Explanation

app packages : 
* com\example\nytimes
    * data
        * retrofit
           * api
        * repo
    * ui
        * article
        * details
        * settings
    * util

* Start with (Article fragment) which load api data and load it into recyclerView 
* if there is no network to get api data -->> show image with no internet connection
* on list item clicked navigate to details fragment that show (title, headLine, article image, shortSummary, byline, published and updated date)
* on details fragment there is fab button to open the article in web browser
* on article fragment there is fab button to open the settings you can chande display mode

# How to build and run:

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

. Download the project code, preferably using `git clone`.
. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
. Check out the relevant code:
    * The application is located in `src/main/java` 
    * Choose the `app` module
    * Connect a device or start an emulator
    * The application will be started on the device/emulator and a series of actions will be performed automatically.





 

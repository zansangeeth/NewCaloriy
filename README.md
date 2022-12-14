# Caloriy

 <a href="https://play.google.com/store/apps/details?id=com.zasa.newcaloriy"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=60px /></a>
 
Sangeeth Amirthanathan

**Caloriy** is an App that will suggest daily meals with users preferenece (How much of calories they want to gain for a day ?)

Time spent: **1** day spent in total

## Android MVVM Architecture

MVVM stands for Model, View, ViewModel.

* [ ] Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.

* [ ] View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.

* [ ] ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.

The following flow illustrates the core MVVM Pattern.

![android-mvvm-pattern](https://user-images.githubusercontent.com/42418189/186920956-39430cc6-9eab-4b5a-86fc-c9cba4b72e3b.png)

## Functionality 

The following **required** functionality is completed:

* [ ] Splash Screen
* [ ] Activities
* [ ] Shimmer Animations - Facebook
* [ ] View Holder
* [ ] Lifecycle
* [ ] Activity Navigations
* [ ] Data tranfer within Activities
* [ ] Webview
* [ ] Retrofit
* [ ] Recycler View
* [ ] Google Ads

The following **extensions** are implemented:

* [ ] User can enter the calorie value
* [ ] User can get recipies informations 
* [ ] User can see the web view of specific recipie item in app.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

![XRecorder_24082-1661411108388](https://user-images.githubusercontent.com/42418189/186598273-e5b3b49e-1271-4ab8-afa2-44018b3f1e36.gif)


# Screenshots
Screen | Search Meals by Calorie Amount | Get Meals | Item Meal Web View |
--- | --- | --- | --- |
Imaages | ![Screenshot_20220822-111342](https://user-images.githubusercontent.com/42418189/185847852-657f24f3-34bf-4e35-8254-a716c61f00a3.png) | ![Screenshot (Oct 1, 2022 9_41_17 AM)](https://user-images.githubusercontent.com/42418189/193391998-55309c3d-5bc3-42b6-a78c-2d06bda7204a.png) | ![Screenshot (Aug 24, 2022 11_00_11 AM)](https://user-images.githubusercontent.com/42418189/186337026-fc42577a-20e7-4de8-b1b7-796891e55069.png) |

## Notes

Describe any challenges encountered while building the app.

* [ ] Navigating Activities
* [ ] Passing data between Activities
* [ ] Dealing with build.gradle
* [ ] Designing Application in XML
* [ ] Design UI
* [ ] Deal with retrofit
* [ ] Deal with data classed and JSON objects classws


## License

    Copyright 2022 Sangeeth Amirthanathan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





 

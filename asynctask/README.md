# AsyncTask

## APK File

Path:
- > CS374/asynctask/apk/asynctask.apk

## Function

### AsyncTask

This app sets the Thread asleep for 0-12 * 200 milliseconds (generates long from 0 to 12 using 
ThreadLocalRandom) when the Button "**SLEEP**" is clicked. When it "awakens", the TextView
becomes updated with "*Finally awakened from my nap after x seconds!*", ***x*** being the
number of milliseconds that the Thread was asleep. This was all done using Android Studio's AsyncTask.

### What it looks like initially

> ![asynctask1](https://raw.githubusercontent.com/PrideInt/CS374/master/asynctask/readme/asynctask1.png)

### When clicked (and Thread is asleep)

> ![asynctask2](https://raw.githubusercontent.com/PrideInt/CS374/master/asynctask/readme/asynctask2.png)

### When it awakens (examples)

> ![asynctask3](https://raw.githubusercontent.com/PrideInt/CS374/master/asynctask/readme/asynctask3.png)

> ![asynctask4](https://raw.githubusercontent.com/PrideInt/CS374/master/asynctask/readme/asynctask4.png)

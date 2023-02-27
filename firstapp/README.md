# FirstApp

## APK File

Path:
- CS374/firstapp/apk/firstapp.apk

## Function

### Anagram

This app updates the TextView with the id of "pridewashere" with an anagram
of "pride" (Not truly an anagram, but just changes the cases of the word "pride")
in the phrase "Pride was here" by clicking the Button labeled "CLICK ME!".

I have done this using a simple StringBuilder as well as String variables containing "pride" and "PRIDE".
I looped this over the length of the word "pride" and on a 50/50 chance, it selects and appends 
a character from the index in the loop from either the String "pride" or "PRIDE", returning the
StringBuilder as a String and adding " was here" to update the "was here" portion of the text.

### What it looks like initially

![firstapp1](https://raw.githubusercontent.com/PrideInt/CS374/master/firstapp/readme/firstapp1.png)

### First click

![firstapp1](https://raw.githubusercontent.com/PrideInt/CS374/master/firstapp/readme/firstapp2.png)

### More examples of the anagram

![firstapp1](https://raw.githubusercontent.com/PrideInt/CS374/master/firstapp/readme/firstapp3.png)

![firstapp1](https://raw.githubusercontent.com/PrideInt/CS374/master/firstapp/readme/firstapp4.png)

![firstapp1](https://raw.githubusercontent.com/PrideInt/CS374/master/firstapp/readme/firstapp5.png)

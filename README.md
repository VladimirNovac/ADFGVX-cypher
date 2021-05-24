# ADFGVX-cypher
This is an expanded version of the ADFGVX cypher to encapsulate a large number of characters.
It has simple user interface that allows for direct on screen encryption / decryption from user input.
In addition to the direct feature, it also has a second option for encrypting existing files.

If the second feature is selected, the user is presented with eight options (text files) from which to choose. All these files are stored in the src/ie folder.
When one of the files is selected, the program will load it from the default folder, encrypt it and save it in the same folder as encrypted.txt
Then, it will work in reverse by loading the encrypted file, decoding it and save it as decrypted.txt
During the tests, this cypher scored an average of 1.8 seconds processing the book War and Peace.

# Usage
The program is divided in 4 classes:
1. Runner - starts the application
2. Menu - houses the user interface and order of operations
3. ADFGVX - this is where the main logic is located
4. ContainerArray - generates an Array used by ADFGVXcypher class

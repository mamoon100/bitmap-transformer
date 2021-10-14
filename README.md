# Bitmap-transformer

This is a bitmap transformation for changing and manipulating bitmaps file.

## Version

- 14/10/2021:
  this app contain three method of editing bitmaps file.
  1. invert - invert the color of bitmap
  2. black and white - change the color of bitmap to black and white
  3. random - change the color of bitmap to random color

## Usage

To Use this Application follow the following steps:

`gradle run --args="bitmapPAth outPath method"`

- the root directory of this project is app so your input and output path should be relative to this directory.
- the generated bmp file will hold the name of Rendered`$originalfilename`.bmp
- for the black and white method type `blackandwhite`.

You can test this with the file exsist in the resources folder with this command (i.e we will choose the random color method).

gradle run --args="src/main/resources/java.bmp src random"

> Note: This Application will receive modification in order to have more method and implement the CLI app with whole interface for the user.

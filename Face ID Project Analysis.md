# Face ID Project Analysis

## initial classes
**Facemark**
3 methods

```swift
public static int[][] getFacemarks(String PhotoNameAndLocation, String CreateMarkers) throws IOException, URISyntaxException, InterruptedException {...}
```
**Return** a 2D int array
**Prameters** `String PhotoNameAndLocation`, `String CreateMakers`.
`CascadeClassifier faceDetector` Load the face detector, create by openCV.

`FacemarkKazemi facemark` Create a facemark object.

`facemark.loadModel` Load the landmark detector

Then load the chosen image `gray` from the `PhotoNameAndLocation`, and create a gray scale image. *Mat Type Image*

Using `faceDetector` to detect multiScale of `gray` image and store it as a `RectVector ` in the object `faces`.

Next `if` statement means this program do not allow **more than 1 face** or **no**  face in the picture.

`Point2fVectorVector landmarks` create a `Point2fVectorVector` pobject for landmarks.

Variable `success` is use to check if the landmark detector runs successfully.

**Return** 
```swift
int[][] FacialCoordinates = new int[68][2];
```
Use this 2D array to store the landmarks find on the face, it has **68** landmarks in total and each landmark has a value of **X** and **Y**.

```swift
public static void AddLineOnPhoto(String PhotoNameAndLocation, Point Start, Point End, String Description) {...}
```
This method is used to draw the line between landmarks, it is a good way for understand **proportion** in human face.

```swift
public static String removeExtension(String s){...}
```
This method is to remove the extension of file path, which is good for naming the file.
**CreateMarkers**
The variable has tow options, 'XandY' and 'drawFacemarks'.
The X axis is from left to right, the Y axis is from up to down, which is reverse from the XY model in math.
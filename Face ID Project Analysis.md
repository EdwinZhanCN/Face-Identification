# Face ID Project Analysis

## initial classes
**Facemark**
3 methods

```java
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
```java
int[][] FacialCoordinates = new int[68][2];
```
Use this 2D array to store the landmarks find on the face, it has **68** landmarks in total and each landmark has a value of **X** and **Y**.

```java
public static void AddLineOnPhoto(String PhotoNameAndLocation, Point Start, Point End, String Description) {...}
```
This method is used to draw the line between landmarks, it is a good way for understand **proportion** in human face.

```java
public static String removeExtension(String s){...}
```
This method is to remove the extension of file path, which is good for naming the file.
**CreateMarkers**
The variable has tow options, 'XandY' and 'drawFacemarks'.
The X axis is from left to right, the Y axis is from up to down, which is reverse from the XY model in math.

**Camera_GUI_System**

`boolean BaseFaceSet;` `static String DatabaseName = ""` 
`Webcam` is used to get the camera on the computer.
This class can automatically create a java software interface and open the camera.
It has originally create 2 constructor:

```java
public Camera_GUI_System(int test){

}//no usage

public Camera_GUI_System(){
	DatabaseName = "FaceID.csv";
	...//get the Camera
	...//create camera resolution
	...//set to 1080p
	...//GUIs
	//TODO: show the 'device owner face'
	...//Last face GUI
	pubilc void actionPerformed(ActionEvent e){
		Thread hilo = new Thread(new Runnable(){
			public void run(){
				...//delete all old base photos.
				int[][] Facemarks = null; //TODO:
				String FacemarSetting = "NumberPoints"// or XandY or 	drawFacemarks
				//take new base photos
				do{
					takeBasePhoto("...");
					Facemarks = Facemark.getFacemarks(..., 										FacemarkSetting);
				}while(Facemarks == null);
				...//Repeat do...while statement to take 3 basephotos
				BaseFaceSet = true;
			}
		})
	}
}

public String takeBasePhoto(String PhotoName){
	...//Imwrite
	return PhotoName;
}

...So many no usages methods.
```

# 改进
在试验的过程中发现，即使是非常清晰的图片，Facemark的位置与预期位置也有极大的偏差，所以计划改变图像的饱和度，灰度以及对比度来提高程序的识别准确率。
## 灰度
输出灰度图片
```java
Mat gray = new Mat();
cvtColor(img, gray, COLOR_BGR2GRAY);
equalizeHist(gray, gray);
```

## 比例

36，45双眼大宽距									

39，42双眼小宽距

36，45，30 鼻三角

1，15，48，54倒梯形

距离公式
$$
\sqrt{(x_1-x_2)^2+(y_1-y_2)^2}
$$

两点直线公式
$$
(y_2-y_1)X+(x_1-x_2)Y+(x_2y_1-x_1y_2)
$$
点到直线距离公式
$$
|\frac{ax+by+c}{\sqrt{A^2+B^2}}|
$$

```java
		System.out.println(calculateDegree(noseX, noseY, lftEyeLX, lftEyeLY, noseUpX, noseUpY));
//计算眼距的夹角，顶点为鼻子中心
        System.out.println(180 - calculateDegree(noseMiddleX, noseMiddleY, lftNoseX,lftNoseY,rightNoseX,rightNoseY));
//计算鼻翼的弧度夹角，顶点为鼻翼中间
        System.out.println((calculateDistanceOfTowPoints(lftEyeLX, lftEyeLY, rightEyeRX, rightEyeRY) / calculateDistanceOfTowPoints(lftFaceUpX, lftFaceUpY, rightFaceUpX, rightFaceUpY))*100);
//计算双眼外距与脸宽的比例
        System.out.println(calculateDistanceOfTowPoints(noseUpX, noseUpY, noseX, noseY)-calculateDistanceOfTowPoints(lftNoseX, lftNoseY, rightNoseX, rightNoseY));
//计算鼻梁长度与鼻翼宽度的比例
		System.out.println(calculateTriangleArea(noseUpX, noseUpY, noseX, noseY, rightEyeRX, rightEyeRY) / calculateTriangleArea(noseUpX, noseUpY, rightFaceMidX, rightFaceMidY, rightFaceTopX,rightFaceTopY));
//计算右眼右顶点鼻梁三角区与鼻梁上顶点和脸部上轮廓线三角区的比例
        System.out.println();
```

在实验过程当中，1号数据再没任何数据调整的情况下，最不精准也无法分辨出同一个人

2号数据表现**最佳**

3号数据表现不好，大多数人的比例**太过相似**。

4号数据表现**不错**，有**较强的可辨识性**，对比奥巴马和特朗普

5号数据在绝对正脸的表现很好，但是**脸部偏移过大**会造成数据毫无意义

目前**眼距**和**鼻距**基本上是最稳定的点，不受**大小**，**倾斜**程度影响。

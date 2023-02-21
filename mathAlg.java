import org.opencv.core.Mat;

public class mathAlg {
    public static void runAlg(int[][] points){

        int noseUpX = points[27][0];
        int noseUpY = -points[27][1];
        int noseX = points[30][0];
        int noseY = -points[30][1];
        int lftEyeLX = points[36][0];
        int lftEyeLY = -points[36][1];
        int lftFaceUpX = points[1][0];
        int lftFaceUpY = -points[1][1];
        int rightFaceUpX = points[15][0];
        int rightFaceUpY = -points[15][1];
        int rightEyeRX = points[45][0];
        int rightEyeRY = -points[45][1];
        //31,35,27,30
        int lftNoseX = points[31][0];
        int lftNoseY = -points[31][1];
        int rightNoseX = points[35][0];
        int rightNoseY = -points[35][1];
        //33
        int noseMiddleX = points[33][0];
        int noseMiddleY = -points[33][1];
        //14,16
        int rightFaceMidX = points[14][0];
        int rightFaceMidY = -points[14][1];
        int rightFaceTopX = points[16][0];
        int rightFaceTopY = -points[16][1];
        //
















        System.out.println(calculateDegree(noseX, noseY, lftEyeLX, lftEyeLY, noseUpX, noseUpY));
        System.out.println(180 - calculateDegree(noseMiddleX, noseMiddleY, lftNoseX,lftNoseY,rightNoseX,rightNoseY));
        System.out.println(calculateDistanceOfTowPoints(rightEyeRX, rightEyeRY, lftEyeLX, lftEyeLY)/calculateDistanceOfTowPoints(rightFaceUpX, rightFaceUpY,lftFaceUpX,lftFaceUpY));
        System.out.println(calculateTriangleArea(noseUpX, noseUpY, noseX, noseY, rightEyeRX, rightEyeRY) / calculateTriangleArea(noseUpX, noseUpY, rightFaceMidX, rightFaceMidY, rightFaceTopX,rightFaceTopY));
        System.out.println();
    }

    public static double calculateDegree(int x1, int y1, int x2, int y2, int x0, int y0){
        double Numerator = (y2-y1)*x0+(x1-x2)*y0+(x2*y1-x1*y2);
        double Denominator = Math.sqrt(Math.pow((y2-y1),2)+ Math.pow((x1-x2),2));
        double hypotenuse = Math.sqrt(Math.pow((x0-x1),2) + Math.pow((y0-y1),2));
        //divide numerator and denominator to get the opposite side, and get the sin value of angle.
        double degree = Math.toDegrees(Math.asin((Numerator/Denominator)/hypotenuse));
        return degree;
    }

    public static double calculateDistanceOfTowPoints(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

    public static double calculateTriangleArea(int peakX, int peakY, int base1X, int base1Y, int base2X, int base2Y){
        double base = calculateDistanceOfTowPoints(base1X, base1Y, base2X, base2Y);
        double height = (base2Y-base1Y)*peakX+(base1X-base2X)*peakY+(base2X*base1Y-base1X*base2Y);
        return base*height;
    }

}

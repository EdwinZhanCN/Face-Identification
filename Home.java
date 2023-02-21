import org.opencv.face.Face;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class Home {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String answer = "T";

        //locations and Directory
        String testLocation = "Photos/sample-photo/Obama/";
        File testDirect = new File(testLocation);
        String camLocation = "Photos/CameraPhotos/";

        //Interaction
        do{
            System.out.println();
            System.out.println("Enter 'T' to run test without open the camera, enter 'R' to run camera, enter 'E' to exist");
            answer = sc.nextLine().toLowerCase();

            if(answer.equals("t")){
                runTest(testDirect,testLocation);
            }else if(answer.equals("r")){
                runCamera();
            }
        }while(!answer.equals("e"));
    }

    public static void runTest(File testDirect, String testLocation) throws IOException, URISyntaxException, InterruptedException {
        String[] ListOfTPhotos = testDirect.list();

        if(!testDirect.exists()){
            System.out.println("Directory not found!");
            System.exit(0);
        }

        assert ListOfTPhotos != null;
        for(String TestPhotoDirect : ListOfTPhotos){
            mathAlg.runAlg((Objects.requireNonNull(Facemark.getFacemarks(testLocation + TestPhotoDirect, "NumberedPoints"))));
//          imageProcessing.toHighSaturation(testLocation + TestPhotoDirect);
        }
    }

    public static void runCamera(){
        Camera_GUI_System Cameras = new Camera_GUI_System();
    }
}

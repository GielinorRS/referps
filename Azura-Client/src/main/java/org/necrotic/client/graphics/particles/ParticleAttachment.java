package org.necrotic.client.graphics.particles;

import java.util.HashMap;
import java.util.Map;

public class ParticleAttachment {

    private static final Map<Integer, int[][]> attachments = new HashMap<>();

    public static int[][] getAttachments(int model) {
        return attachments.get(model);
    }

    static {

       // attachments.put(39055, new int[][] { {235, 9}});


        // the 0 in the 2d array is the definition id, so it knows which defs we want this particle to load.
    	// eternal cape
        attachments.put(65434, new int[][] { {23, 4},{98, 4},{25, 4},{56, 4},{23, 4},{55, 4},{7, 4}, {2, 4} });
        attachments.put(65436, new int[][] { {108, 4},{63, 4},{25, 4},{2, 4},{165, 4},{120, 4},{160, 4}, {114, 4}, {383, 4} });
        attachments.put(65438, new int[][] { {99, 4},{65, 4},{106, 4},{74, 4},{121, 4},{159, 4},{6, 4}, {31, 4}, {22, 4} , {177, 4}, {190, 4}});
//water
      //  attachments.put(65475, new int[][] { {23, 6},{98, 6},{25, 6},{56, 6},{23, 6},{55, 6},{7, 6}, {2, 6} });
        attachments.put(65477, new int[][] { {108, 6},{63, 6},{25, 6},{2, 6},{165, 6},{120, 6},{160, 6}, {114, 6}, {383, 6} });
        attachments.put(65479, new int[][] { {99, 6},{65, 6},{106, 6},{74, 6},{121, 6},{159, 6},{6, 6}, {31, 6}, {22, 6} , {177, 6}, {190, 6}});
//robin cape
        attachments.put(64366, new int[][] { {44, 6},{45, 6},{87, 6},{86, 6},{82, 6}, {83, 6} });

        //
        attachments.put(65105, new int[][] { {329, 3}, {322, 3}, {302, 3}, {300, 3}, {250, 3}, {282, 3}, {283, 3}, {285, 3}, {252, 3}, {286, 3} });
        //max cape
        attachments.put(65334, new int[][] { {270, 1}, {269, 1}, {49, 1}, {45, 1}, {38, 1}, {36, 1}, {17, 1}, {40, 1}, {283, 1}, {310, 1}, {315, 1} });
        //ok lets say u wann add particles to the tokhaar kal cape.
        //model - verticle - particle idep exactly. did u see where i got the vertice id from? yes perfect. ok so lets say u want to give tokhaar kal cape
        //particle id 1, just set the 2nd number in the array to the defintiion if u want it to use.
        ///it's pretty straight forward once you do it a few times.
        //Viggora chainmace
 attachments.put(65323, new int[][] { {221, 2}, {235, 2}, {237, 2}, {223, 2}, {219, 2}, {20, 2}, {10, 2} });
 //Scythe of vitur -65321
// attachments.put(65321, new int[][] { {192, 4}, {389, 4}, {387, 4}, {388, 3}, {196, 4}, {177, 4}, {158, 4}, {163, 4}, {187, 4}, {188, 4}, {386, 4} });
//Twisted bow
        attachments.put(65325, new int[][] { {184, 4}, {141, 4}, {122, 4}, {105, 3}, {103, 4}, {30, 4}, {25, 4}, {87, 4}, {100, 4}, {4, 4} });

        attachments.put(139984, new int[][] { {184, 9}, {141, 9}, {122, 9}, {105, 9}, {103, 9}, {30, 9}, {25, 9}, {87, 9}, {100, 9}, {4, 9} });

        //GRAND MAX ITEMS.
 attachments.put(64396, new int[][] { {419, 8}});
 attachments.put(64398, new int[][] { {478, 7},{235, 7}});//
 attachments.put(64400, new int[][] { {50, 7}});
// attachments.put(64402, new int[][] { {151, 7},{127, 7}});//

    }
}

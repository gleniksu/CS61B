import es.datastructur.synthesizer.GuitarString;
public class GuitarHero {

    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] gsa= new GuitarString[keyboard.length()];
    private  static double powFactor;

    public static void main(String[] args) {
        for(int i = 0; i < gsa.length; i++) {
            powFactor = (i - 24) / 12;
            gsa[i] = new GuitarString(440 * Math.pow(2, powFactor));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                int index;
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                gsa[index].pluck();
            }
            double sample = 0;
            for (int i = 0; i < gsa.length; i++) {
                sample += gsa[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < gsa.length; i++) {
                gsa[i].tic();
            }
        }


    }
}

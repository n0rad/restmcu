package net.awired.restmcu.it;


public class BenchBoard {

    //    @Rule
    //    public static RestMcuTestRule hcc = new RestMcuTestRule();
    //
    //    private static int i = 0;
    //    private static long timestamp = System.currentTimeMillis();
    //
    //    @Path("/")
    //    public static class testNotify implements RestMcuNotifyResource {
    //        @Override
    //        public void pinNotification(RestMcuPinNotification pinNotification) {
    //        }
    //
    //        @Override
    //        public void boardNotification(RestMcuBoardNotification boardNotification) {
    //            try {
    //                if (i % 2 == 0) {
    //                    hcc.getPinResource().setPinValue(3, 1f);
    //                } else {
    //                    hcc.getPinResource().setPinValue(3, 0f);
    //                }
    //            } catch (NotFoundException e) {
    //                // TODO Auto-generated catch block
    //                e.printStackTrace();
    //            } catch (UpdateException e) {
    //                // TODO Auto-generated catch block
    //                e.printStackTrace();
    //            }
    //
    //            if (i++ == 200) {
    //                System.out.println("duration : " + (System.currentTimeMillis() - timestamp));
    //                timestamp = System.currentTimeMillis();
    //                i = 0;
    //            }
    //        }
    //    }
    //
    //    @ClassRule
    //    public static NotifyServerRule server = new NotifyServerRule(8080, false, testNotify.class);
    //
    //    //    @Test
    //    public void should_toggle_green_led2() throws Exception {
    //        RestMcuBoardResource boardResource = hcc.getBoardResource();
    //        for (int i = 0; i < 100; i++) {
    //            //            boardResource.getBoard();
    //            //                        pinResource.getPinValue(3);
    //            hcc.getPinResource().setPinValue(3, 1f);
    //            hcc.getPinResource().setPinValue(3, 0f);
    //        }
    //        long currentTimeMillis = System.currentTimeMillis();
    //        for (int i = 0; i < 10000; i++) {
    //            boardResource.getBoard();
    //            //            pinResource.getPinValue(3);
    //            hcc.getPinResource().setPinValue(3, 1f);
    //            hcc.getPinResource().setPinValue(3, 0f);
    //        }
    //        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    //    }
    //
    //    //    @Test
    //    public void should_toggle_green_led5() throws Exception {
    //        RestMcuBoardResource boardResource = hcc.getBoardResource();
    //        for (int i = 0; i < 100; i++) {
    //            //            boardResource.getBoard();
    //            //                        pinResource.getPinValue(3);
    //            hcc.getPinResource().setPinValue(5, 1f);
    //            hcc.getPinResource().setPinValue(5, 0f);
    //        }
    //        long currentTimeMillis = System.currentTimeMillis();
    //        for (int i = 0; i < 10000; i++) {
    //            boardResource.getBoard();
    //            //            pinResource.getPinValue(3);
    //            hcc.getPinResource().setPinValue(5, 1f);
    //            hcc.getPinResource().setPinValue(5, 0f);
    //        }
    //        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    //    }
    //
    //    //    @Test
    //    public void test() throws Exception {
    //
    //        URL url = new URL("http://192.168.42.244");
    //        for (int i = 0; i < 100; i++) {
    //            hcc.getBoardResource().getBoard();
    //        }
    //        long currentTimeMillis = System.currentTimeMillis();
    //        for (int i = 0; i < 200; i++) {
    //            hcc.getBoardResource().getBoard();
    //        }
    //        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    //    }
    //
    //    private void dfdf(URL url) throws IOException {
    //
    //        InputStream openStream = url.openStream();
    //
    //        try {
    //
    //            int read;
    //            byte[] cbuf = new byte[1024];
    //            while ((read = openStream.read(cbuf)) != -1) {
    //
    //            }
    //
    //        } finally {
    //            openStream.close();
    //        }
    //    }

}

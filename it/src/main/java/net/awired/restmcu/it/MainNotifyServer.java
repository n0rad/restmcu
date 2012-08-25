package net.awired.restmcu.it;


public class MainNotifyServer {

    //    public static RestMcuTestRule hcc;
    //
    //    public static RestMcuPinNotification notification;
    //
    //    @Path("/")
    //    public static class testNotify implements RestMcuNotifyResource {
    //        @Override
    //        public void pinNotification(RestMcuPinNotification pinNotification) {
    //            System.out.println("received pin notification :" + pinNotification);
    //            if (pinNotification.getId() == 4) {
    //                try {
    //                    hcc.getPinResource().setPinValue(5, pinNotification.getValue());
    //                } catch (NotFoundException e) {
    //                    // TODO Auto-generated catch block
    //                    e.printStackTrace();
    //                } catch (UpdateException e) {
    //                    // TODO Auto-generated catch block
    //                    e.printStackTrace();
    //                }
    //            }
    //            if (pinNotification.getId() == 2) {
    //                try {
    //                    hcc.getPinResource().setPinValue(3, pinNotification.getValue());
    //                } catch (NotFoundException e) {
    //                    // TODO Auto-generated catch block
    //                    e.printStackTrace();
    //                } catch (UpdateException e) {
    //                    // TODO Auto-generated catch block
    //                    e.printStackTrace();
    //                }
    //            }
    //
    //        }
    //
    //        @Override
    //        public void boardNotification(RestMcuBoardNotification boardNotification) {
    //            System.out.println("received board notification :" + boardNotification);
    //        }
    //
    //    }
    //
    //    public static void main(String[] args) throws Throwable {
    //        new MainNotifyServer(args);
    //    }
    //
    //    public MainNotifyServer(String[] args) throws Throwable {
    //        NotifyServerRule notifyServer = new NotifyServerRule(8080, true, testNotify.class);
    //        notifyServer.before();
    //        hcc = new RestMcuTestRule("http://192.168.42.244");
    //        hcc.before();
    //    }
    //
    //    public void run() throws Throwable {
    //        while (true) {
    //            Thread.sleep(1000);
    //        }
    //    }

}

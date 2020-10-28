package task12_readerwriterproblem;

public class MyWriter extends MyRW {

    public MyWriter(int writer_id, MyDataBase dbLink) {
        super("Writer", writer_id, dbLink, 5000);
    }


    @Override
    public void run() {
        while (true) {
            try {
                long timeOut = getTimeOut();
                Main.myPrinter(this.toString() + " is sleeping (" + timeOut/1000.0 + " c.).");
                Thread.sleep(timeOut);
                Main.myPrinter(this.toString() + " wants to write.");
                m_dbLink.startWrite(this.toString());
                m_dbLink.endWrite(this.toString());
            }
            catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
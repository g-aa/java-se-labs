package task12_readerwriterproblem;

public class MySemaphore {

    private int m_maxPermission;
    private int m_curPermission;

    private final Object m_look;

    public MySemaphore(int permission) {
        m_maxPermission = permission;
        m_curPermission = 0;
        m_look = new Object();
    }

    // получить разрешение на использование ресурса:
    public void acquire() throws InterruptedException {
        synchronized (m_look){
            m_curPermission++;
            if (m_curPermission > m_maxPermission){
                m_look.wait();
            }
        }
    }

    // вернуть разрешение на пользование ресурсом:
    public void release() {
        synchronized (m_look){
            m_curPermission--;
            m_look.notify();
        }
    }
}
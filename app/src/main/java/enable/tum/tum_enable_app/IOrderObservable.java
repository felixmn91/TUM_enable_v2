package enable.tum.tum_enable_app;

/**
 * Created by Lennart Mittag on 14.12.2015.
 */
public interface IOrderObservable
{
    public void registerAsObserver(IOrderObserver observer);
    public void informObserverChangeHasOccurred();
}

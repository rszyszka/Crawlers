package crawler;

import java.util.EventListener;

@FunctionalInterface
public interface IEmptyListener extends EventListener 
{
	void handle( Object source );
}

package team.ixigo.hack.com.team1.manager;


import team.ixigo.hack.com.team1.exception.IxigoException;

public class ExceptionManager
{
	//Singleton Class
	public static ExceptionManager exceptionManager;
	private boolean isTaskCompleted = true;

	//Constructor
	private ExceptionManager()
	{

	}

	public static ExceptionManager getInstance()
	{
		if(exceptionManager == null)
		{
			exceptionManager = new ExceptionManager();
		}

		return exceptionManager;
	}

	public void setTaskCompletionFailed()
	{
		isTaskCompleted = false;
	}

	public boolean isTaskCompleted()
	{
		return isTaskCompleted;
	}


	public static void dispatchExceptionDetails(int exceptionCode, String exceptionMessage, Exception exceptionObject) throws IxigoException
	{
		if(exceptionObject instanceof IxigoException)
		{
			throw (IxigoException) exceptionObject;
		}
		else
		{
			//Throw Exception
			throw new IxigoException(exceptionCode, exceptionMessage);
		}
	}
}

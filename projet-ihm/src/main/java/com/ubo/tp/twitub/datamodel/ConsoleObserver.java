package main.java.com.ubo.tp.twitub.datamodel;

public class ConsoleObserver implements IDatabaseObserver{

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
	//	System.out.println("twit add");
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println(addedUser);
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}

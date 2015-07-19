public class ConfigModel {
	private int quantTargets;
	private int chanceBoom;
	private int chanceFishing;
	private int chanceCherish;
	
	public ConfigModel(String diffic)
	{
		if( diffic.compareTo("easy") == 0)
		{
			setConfigEasy();
		}
		else if( diffic.compareTo("medium") == 0)
		{
			setConfigMed();
		}
		else
		{
			setConfigHard();
		}
	}
	
	public ConfigModel()
	{
		quantTargets = 10;
		chanceBoom = 50;
		chanceFishing = 50;
		chanceCherish = 10;
	}

	private void setConfigHard() 
	{
		quantTargets = 10;
		chanceBoom = 60;
		chanceFishing = 35;
		chanceCherish = 5;
	}

	private void setConfigMed() 
	{
		quantTargets = 10;
		chanceBoom = 50;
		chanceFishing = 50;
		chanceCherish = 10;
	}

	private void setConfigEasy() 
	{
		quantTargets = 10;
		chanceBoom = 15;
		chanceFishing = 50;
		chanceCherish = 20;
	}
	
	public int getQuantTargets() {
		return quantTargets;
	}

	public void setQuantTargets(int quantTargets) {
		this.quantTargets = quantTargets;
	}

	public int getChanceBoom() {
		return chanceBoom;
	}

	public void setChanceBoom(int chanceBoom) {
		this.chanceBoom = chanceBoom;
	}

	public int getChanceFishing() {
		return chanceFishing;
	}

	public void setChanceFishing(int chanceFishing) {
		this.chanceFishing = chanceFishing;
	}

	public int getChanceCherish() {
		return chanceCherish;
	}

	public void setChanceCherish(int chanceCherish) {
		this.chanceCherish = chanceCherish;
	}
}

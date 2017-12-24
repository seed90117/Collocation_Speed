package Values;

public class Data {

	private int column = 0; // 總欄數
	private int row = 0; // 總行數
	private PersonData[] personData = null; // 個人資料
	private int[] group = null; // 分群資料
	
	private static Data instance = null;
	private Data(){}
	
	public static synchronized Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setPersonDataSize(int size) {
		this.personData = new PersonData[size];
		this.group = new int[size];
	}
	
	public void setPersonData(int index, Item item) {
		if (this.personData[index] == null) {
			this.personData[index] = new PersonData();
		}
		this.personData[index].addPersonData(item);
	}
	
	public void setPersonDataGroup(int index, int grouping) {
		this.group[index] = grouping;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getPersinDataSize() {
		return this.personData.length;
	}
	// 取得個人資料物件
	public PersonData getPersinData(int index) {
		return this.personData[index];
	}
	// 取得個人資料內特定欄位
	public Item getPersonData(int personCount, int itemCount) {
		return this.personData[personCount].getPersonData(itemCount);
	}
	// 取得個人資料內特定欄位值
	public String getPersonDataItemValue(int personCount, String itemName) {
//		String reString = "";
//		int size = this.personData[personCount].getPersonDataSize();
//		for (int i=0;i<size;i++) {
//			Item item = this.personData[personCount].getPersonData(i);
//			if (item.item.equals(itemName)) {
//				reString = item.value;
//				break;
//			}
// 		}
		return this.personData[personCount].getPersonData(itemName);
	}
}

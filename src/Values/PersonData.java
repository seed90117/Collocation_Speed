package Values;

import java.util.ArrayList;

public class PersonData {
	
	private ArrayList<Item> personData = new ArrayList<>(); // 個人資料List，放入一個Item表示欄位名稱與其值
	
	public void addPersonData(Item item) {
		this.personData.add(item);
	}
	
	public int getPersonDataSize() {
		return this.personData.size();
	}
	
	public Item getPersonData(int index) {
		return this.personData.get(index);
	}
	// 取得特定欄位資料
	public String getPersonData(String itemName) {
		String reStr = "";
		for (int i=0;i<this.personData.size();i++) {
			Item item = this.personData.get(i);
			if (item.item.equals(itemName)) {
				reStr = item.value;
				break;
			}
		}
		return reStr;
	}
}

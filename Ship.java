package Assignment;

public class Ship {
	//Private variables
   private String name;
   private String owner;
   private int value;
   private String company;
   private int maxLoad;
   private int basicLoad;
   private int basicPrice;
   private int heavyLoad;
   private int heavyPrice;
   private int refrigLoad;
   private int refrigPrice;
   private int specialLoad;
   private int currentMaxLoad;
   private int currentBasicLoad;
   private int currentHeavyLoad;
   private int currentRefrigLoad;
   private int currentSpecialTotal = 0;
   private int currentSpecialBasic = 0;
   private int currentSpecialHeavy = 0;
   private int currentSpecialRefrig = 0;
   private int currentCost = 0;
   private boolean specialIsFull = false;
   private boolean basicIsFull = false;
   private boolean heavyIsFull = false;
   private boolean refrigIsFull = false;
	
	
	//Constructor
   public Ship(String name, String owner, int value, String company, int maxLoad){
      this.name = name;
      this.owner = owner;
      this.value = value;
      this.company = company;
      this.maxLoad = maxLoad;
   	
   }
	
   public Ship(String name, String owner, int value, String company, int maxLoad, int basicLoad, int basicPrice,
   		int heavyLoad, int heavyPrice, int refrigLoad, int refrigPrice, int specialLoad) {
      this.name = name;
      this.owner = owner;
      this.value = value;
      this.company = company;
      this.maxLoad = maxLoad;
      this.basicLoad = basicLoad;
      this.basicPrice = basicPrice;
      this.heavyLoad = heavyLoad;
      this.heavyPrice = heavyPrice;
      this.refrigLoad = refrigLoad;
      this.refrigPrice = refrigPrice;
      this.setSpecialLoad(specialLoad);
      this.currentMaxLoad = 0;
      this.currentBasicLoad = 0;
      this.currentHeavyLoad = 0;
      this.currentRefrigLoad = 0;
   	
   }

	//Getter & Setter
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getOwner() {
      return owner;
   }
   public void setOwner(String owner) {
      this.owner = owner;
   }

   public int getValue() {
      return value;
   }
   public void setValue(int value) {
      this.value = value;
   }

   public String getCompany() {
      return company;
   }
   public void setCompany(String company) {
      this.company = company;
   }

   public int getMaxLoad() {
      return maxLoad;
   }
   public void setMaxLoad(int maxLoad) {
      this.maxLoad = maxLoad;
   }

   public int getBasicLoad() {
      return basicLoad;
   }
   public void setBasicLoad(int basicLoad) {
      this.basicLoad = basicLoad;
   }

   public int getBasicPrice() {
      return basicPrice;
   }
   public void setBasicPrice(int basicPrice) {
      this.basicPrice = basicPrice;
   }

   public int getHeavyLoad() {
      return heavyLoad;
   }
   public void setHeavyLoad(int heavyLoad) {
      this.heavyLoad = heavyLoad;
   }

   public int getHeavyPrice() {
      return heavyPrice;
   }
   public void setHeavyPrice(int heavyPrice) {
      this.heavyPrice = heavyPrice;
   }

   public int getRefrigLoad() {
      return refrigLoad;
   }
   public void setRefrigLoad(int refrigLoad) {
      this.refrigLoad = refrigLoad;
   }

   public int getRefrigPrice() {
      return refrigPrice;
   }
   public void setRefrigPrice(int refrigPrice) {
      this.refrigPrice = refrigPrice;
   }

	
   public int getCurrentMaxLoad() {
      return currentMaxLoad;
   }

	
   public int getCurrentBasicLoad() {
      return currentBasicLoad;
   }

   public void setCurrentBasicLoad(int currentBasicLoad) {
      int difference = currentBasicLoad - this.currentBasicLoad;
      currentMaxLoad += difference;
      currentCost += difference * basicPrice;
      this.currentBasicLoad = currentBasicLoad;
      if (currentBasicLoad == basicLoad)
         basicIsFull = true;
      else
         basicIsFull = false;
   }

	
   public int getCurrentHeavyLoad() {
      return currentHeavyLoad;
   }

   public void setCurrentHeavyLoad(int currentHeavyLoad) {
      int difference = currentHeavyLoad - this.currentHeavyLoad;
      currentMaxLoad += difference;
      currentCost += difference * heavyPrice;
      this.currentHeavyLoad = currentHeavyLoad;
      if (currentHeavyLoad == heavyLoad)
         heavyIsFull = true;
      else
         heavyIsFull = false;
   }

	
   public int getCurrentRefrigLoad() {
      return currentRefrigLoad;
   }

   public void setCurrentRefrigLoad(int currentRefrigLoad) {
      int difference = currentRefrigLoad - this.currentRefrigLoad;
      currentMaxLoad += difference;
      currentCost += difference * refrigPrice;
      this.currentRefrigLoad = currentRefrigLoad;
      if (currentRefrigLoad == refrigLoad)
         refrigIsFull = true;
      else
         refrigIsFull = false;
   }

   public int getSpecialLoad() {
      return specialLoad;
   }

   public void setSpecialLoad(int specialLoad) {
      this.specialLoad = specialLoad;
   }
   public int getCurrentCost(){
      return currentCost;
   }
   public int getCurrentSpecialTotal(){
      return currentSpecialTotal;}
   public int getCurrentSpecialBasic(){
      return currentSpecialBasic;}
   public int getCurrentSpecialHeavy(){
      return currentSpecialHeavy;}
   public int getCurrentSpecialRefrig(){
      return currentSpecialRefrig;}

   public void setCurrentSpecialBasic(int currentSpecialBasic){
      int difference = currentSpecialBasic - this.currentSpecialBasic;
      this.currentSpecialBasic = currentSpecialBasic;
      currentCost += difference * basicPrice;
      currentSpecialTotal +=difference;
      currentBasicLoad += difference;
      if (currentSpecialTotal == specialLoad)
         specialIsFull = true;
      else
         specialIsFull = false;
      if (currentBasicLoad == basicLoad)
         basicIsFull = true;
      else
         basicIsFull = false;
   }

   public void setCurrentSpecialHeavy(int currentSpecialHeavy){
      int difference = currentSpecialHeavy - this.currentSpecialHeavy;
      this.currentSpecialHeavy = currentSpecialHeavy;
      currentCost += difference * heavyPrice;
      currentSpecialTotal +=difference;
      currentHeavyLoad += difference;
      if (currentSpecialTotal == specialLoad)
         specialIsFull = true;
      else
         specialIsFull = false;
      if (currentHeavyLoad == heavyLoad)
         heavyIsFull = true;
      else
         heavyIsFull = false;
   }

   public void setCurrentSpecialRefrig(int currentSpecialRefrig){
      int difference = currentSpecialRefrig - this.currentSpecialRefrig;
      this.currentSpecialRefrig = currentSpecialRefrig;
      currentCost += difference * refrigPrice;
      currentSpecialTotal +=difference;
      currentRefrigLoad += difference;
      if (currentSpecialTotal == specialLoad)
         specialIsFull = true;
      else
         specialIsFull = false;
      if (currentRefrigLoad == refrigLoad)
         refrigIsFull = true;
      else
         refrigIsFull = false;
   }
   public boolean isSpecialFull(){
      return specialIsFull;}

   public boolean isBasicFull(){
      return basicIsFull;}
   public boolean isHeavyFull(){
      return heavyIsFull;}
   public boolean isRefrigFull(){
      return refrigIsFull;}
}

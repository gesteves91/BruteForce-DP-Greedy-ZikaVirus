
public class List {
  private static class Cell { Object item; Cell next, ant; }
  private Cell first, last, pos, focus1, focus2;
  public List () {
    this.first = new Cell (); this.pos = this.first;
    this.last = this.first; 
    this.first.next = null; this.first.ant = null;
    this.focus1 = new Cell();
    this.focus2 = new Cell();
  }
  /*
  public Object search (Object key) {
    if (this.empty () || key == null) return null;
    Cell aux = this.first;
    while (aux.next != null) {
      if (aux.next.item.equals (key)) return aux.next.item;
      aux = aux.next;
    }    
    return null;
  }*/
  public void insert (Object x) {
    this.last.next = new Cell (); 
    this.last.next.ant = this.last;
    this.last = this.last.next;
    this.last.item = x; this.last.next = null;
  }
  public void insertFocuses (Object x, Object y){
	  //this.focus1.next = new Cell(); 
	  //this.focus1 = this.focus1.next;
	  //this.focus2.next = new Cell(); 
	  //this.focus2 = this.focus2.next;
	  this.focus1.item = x;
	  this.focus2.item = y;
	  //this.focus1.next = null;
	  //this.focus2.next = null;
  }
  public Object getFocus1(){
	  Object obj1 = this.focus1.item;
	  return obj1;
  }
  public Object getFocus2(){
	  Object obj2 = this.focus2.item;
	  return obj2;
  }
  /*
  public Object get (Object key) throws Exception {
    if (this.empty () || (key == null))
      throw new Exception ("Erro: Lista empty ou key invalida");
    Cell aux = this.first;
    while (aux.next != null && !aux.next.item.equals (key)) 
      aux = aux.next;
    if (aux.next == null) return null; 
    Cell q = aux.next; Object item = q.item; aux.next = q.next;
    if (aux.next == null) this.last = aux;
    return item;
  }
  public Object getfirst () throws Exception {
    if (this.empty ()) throw new Exception ("Erro: Lista empty");    
    Cell aux = this.first; Cell q = aux.next;
    Object item = q.item; aux.next = q.next;
    if (aux.next == null) this.last = aux;
    return item;
  }*/
  /*
  public boolean empty () { return (this.first == this.last); } 
  public void imprime () {
    Cell aux = this.first.next;
    while (aux != null) {
      System.out.println (aux.item.toString ()); aux = aux.next;
    }
  }
  public boolean isOnTheList (Object key) {
    Object obj = this.search (key);
    return (obj != null);
  }
  */
  public Object first(){
    this.pos = this.first; return this.next ();
  }

  public Object next(){
    this.pos = this.pos.next;
    if (this.pos == null) return null; 
    else return this.pos.item;
  }
  
  public Object previous() {
	  this.pos = this.pos.ant;
	  if(this.pos == null) return null;
	  else return this.pos.item;
  }

}



public class ClasstoString {
	static String ToString(User user){
		StringBuffer s=new StringBuffer();
		for(int i=0;i<user.getName().length();i++)
			s.append(user.getName().charAt(i));
		for(int i=user.getName().length();i<30;i++)
			s.append(' ');
		for(int i=0;i<user.getRole().length();i++)
			s.append(user.getRole().charAt(i));
		return s.toString();
	}
static String ToString(Doc doc){
	StringBuffer s=new StringBuffer();
	for(int i=0;i<doc.getID().length();i++)
		s.append(doc.getID().charAt(i));
	for(int i=doc.getID().length();i<10;i++)
		s.append(' ');
	for(int i=10;i<10+doc.getCreator().length();i++)
		s.append(doc.getCreator().charAt(i-10));
	for(int i=10+doc.getCreator().length();i<20;i++)
		s.append(' ');
	for(int i=20;i<20+doc.getDescription().length();i++)
		s.append(doc.getDescription().charAt(i-20));
	for(int i=20+doc.getDescription().length();i<45;i++)
		s.append(' ');
	for(int i=50;i<50+doc.getFilename().length();i++)
		s.append(doc.getFilename().charAt(i-50));
	return s.toString();
	}

}

#define pattern
echo 'Define pattern...';
pattern='';
while read line;
do
	if [[ $pattern != '' ]]; 
	then pattern=$pattern'|'; 
	fi;
	pattern=$pattern$line;
done < ignores.txt;
echo $pattern;

#define targets
echo 'Define targets...';
svn status | grep -e'?' | cut -c9-80 | grep -E $pattern > status.lst;
cat status.lst | cut -f1 -d'/' | uniq > status2.lst;
cat status.lst | cut -f1,2 -d'/' | grep -v -E $pattern | uniq >> status2.lst;
cat status2.lst;

#set ignores
echo 'Set ignores...';
while read line;
do
	svn propset svn:ignore --file ignores.txt "./$line";
done < status2.lst;

rm status.lst;
rm status2.lst;

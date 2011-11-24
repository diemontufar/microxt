#define pattern
echo 'Define pattern...';
pattern='';
while read line;
do
	if [[ $pattern != '' ]]; 
	then pattern=$pattern'\|'; 
	fi;
	pattern=$pattern'.*'$line;
done < ignores.txt;
echo $pattern;

#define targets
echo 'Define targets...';
find . -regex $pattern > status.lst;

#removes
echo 'Set removes...';
while read line;
do
	svn delete --force "$line";
done < status.lst;

rm status.lst;

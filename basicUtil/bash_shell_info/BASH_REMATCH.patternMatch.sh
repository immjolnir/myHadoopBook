HADOOP_VERSION=1.2.1
# Save the regex to a var to workaround quoting incompatabilities
# between Bash 3.1 and 3.2
hadoop_version_re="^([[:digit:]]+)\.([[:digit:]]+)(\.([[:digit:]]+))?.*$"
function patternMatch {
HADOOP_VERSION=$1
if [[ "$HADOOP_VERSION" =~ $hadoop_version_re ]]; then
    hadoop_major_ver=${BASH_REMATCH[1]}
    hadoop_minor_ver=${BASH_REMATCH[2]}
    hadoop_patch_ver=${BASH_REMATCH[4]}
else
    echo "Unable to determine Hadoop version information."
    echo "'hadoop version' returned:"
    echo `$HADOOP version`
    exit 5
fi
}

patternMatch $HADOOP_VERSION
HADOOP_VERSION=1.23.585
patternMatch $HADOOP_VERSION
HADOOP_VERSION=2.x.x
patternMatch $HADOOP_VERSION

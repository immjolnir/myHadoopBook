cat << EOF | cat > package-info.java

/*
 * Generated by scripts/saveVersion.sh
 */
@VersionAnnotation(version="VERSION", revision="REV", branch="BRANCH",
                         user="USER", date="DATE", url="URL",
                         srcChecksum="SRCCHECKSUM")
package org.apache.flume;
EOF
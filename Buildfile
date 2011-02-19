# Apache Buildr build file
# So incredibly requires buildr.

# Abusing maven repositories without the pom.xml
# horror and the terribad delays :D
repositories.remote << 'http://mirrors.ibiblio.org/pub/mirrors/maven2/'

# lwjgl's maven repository
repositories.remote << 'http://adterrasperaspera.com/lwjgl'

# Dependencies
# maven-style --> 'group.namespace:artifactid:version'
LWJGL_VERSION = '2.7.1'

LWJGL = group('lwjgl', 'lwjgl-util', 'lwjgl-native',
  :under => 'org.lwjgl', :version =>'2.7.1')

# Jar manifest

# Project description
desc 'EJSCS Pong Server Container Reference Implementation'
define 'ESCSPong' do
  project.version = '0.1.0'
  compile.with transitive(LWJGL)
  package(:jar).with :manifest => manifest.merge(
			  'Main-Class' => 'pong.Game',
  )
end


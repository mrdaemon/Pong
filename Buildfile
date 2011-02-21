# Apache Buildr build file
# So incredibly requires buildr.
# Contributed by Alexandre Gauthier <alex@lab.underwares.org>
# https://github.com/mrdaemon/
#
# For the lazy or impatient:
#   1) gem install buildr
#   2) buildr package
# Your jar should be sitting in 'target/'

# Abusing maven repositories without the pom.xml
# horror and the terribad delays :D
repositories.remote << 'http://mirrors.ibiblio.org/pub/mirrors/maven2/'

# lwjgl's maven repository
repositories.remote << 'http://adterrasperaspera.com/lwjgl'

# Dependencies
LWJGL_VERSION = '2.7.1'
LWJGL = group('lwjgl', 'lwjgl-util', 'lwjgl-native',
  :under => 'org.lwjgl', :version => LWJGL_VERSION)

# Project description
desc 'EJSCS Pong Server Container Reference Implementation'
define 'ESCSPong' do
  project.version = '0.1.0'
  compile.with transitive(LWJGL)
  package(:jar).with :manifest => manifest.merge('Main-Class' => 'pong.Game')
end

# vim: set ft=ruby et ts=8 sw=2 sts=2: 

repositories.remote << 'https://repo1.maven.org/maven2'

MAPS = transitive('com.google.maps:google-maps-services:jar:0.1.20')

my_layout = Layout.new
my_layout[:source, :main, :java] = 'src'
my_layout[:target, :main, :classes] = 'bin'

Project.local_task :run

define 'x-facteur', :layout => my_layout do
	project.version = '20170612'
	compile.with MAPS
	package :jar

	run.using :main => 'xfacteur.XFacteur'
end


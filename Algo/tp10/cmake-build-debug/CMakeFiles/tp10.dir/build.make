# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.16

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = C:\Users\smani\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\201.7846.88\bin\cmake\win\bin\cmake.exe

# The command to remove a file.
RM = C:\Users\smani\AppData\Local\JetBrains\Toolbox\apps\CLion\ch-0\201.7846.88\bin\cmake\win\bin\cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\smani\Documents\GitHub\Cours\Algo\tp10

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/tp10.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/tp10.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/tp10.dir/flags.make

CMakeFiles/tp10.dir/avl.c.obj: CMakeFiles/tp10.dir/flags.make
CMakeFiles/tp10.dir/avl.c.obj: CMakeFiles/tp10.dir/includes_C.rsp
CMakeFiles/tp10.dir/avl.c.obj: ../avl.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/tp10.dir/avl.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\tp10.dir\avl.c.obj   -c C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\avl.c

CMakeFiles/tp10.dir/avl.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/tp10.dir/avl.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\avl.c > CMakeFiles\tp10.dir\avl.c.i

CMakeFiles/tp10.dir/avl.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/tp10.dir/avl.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\avl.c -o CMakeFiles\tp10.dir\avl.c.s

CMakeFiles/tp10.dir/bst.c.obj: CMakeFiles/tp10.dir/flags.make
CMakeFiles/tp10.dir/bst.c.obj: CMakeFiles/tp10.dir/includes_C.rsp
CMakeFiles/tp10.dir/bst.c.obj: ../bst.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/tp10.dir/bst.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\tp10.dir\bst.c.obj   -c C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\bst.c

CMakeFiles/tp10.dir/bst.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/tp10.dir/bst.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\bst.c > CMakeFiles\tp10.dir\bst.c.i

CMakeFiles/tp10.dir/bst.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/tp10.dir/bst.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\bst.c -o CMakeFiles\tp10.dir\bst.c.s

CMakeFiles/tp10.dir/tp10.c.obj: CMakeFiles/tp10.dir/flags.make
CMakeFiles/tp10.dir/tp10.c.obj: CMakeFiles/tp10.dir/includes_C.rsp
CMakeFiles/tp10.dir/tp10.c.obj: ../tp10.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/tp10.dir/tp10.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\tp10.dir\tp10.c.obj   -c C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\tp10.c

CMakeFiles/tp10.dir/tp10.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/tp10.dir/tp10.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\tp10.c > CMakeFiles\tp10.dir\tp10.c.i

CMakeFiles/tp10.dir/tp10.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/tp10.dir/tp10.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\tp10.c -o CMakeFiles\tp10.dir\tp10.c.s

CMakeFiles/tp10.dir/visualtree.c.obj: CMakeFiles/tp10.dir/flags.make
CMakeFiles/tp10.dir/visualtree.c.obj: CMakeFiles/tp10.dir/includes_C.rsp
CMakeFiles/tp10.dir/visualtree.c.obj: ../visualtree.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object CMakeFiles/tp10.dir/visualtree.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\tp10.dir\visualtree.c.obj   -c C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\visualtree.c

CMakeFiles/tp10.dir/visualtree.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/tp10.dir/visualtree.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\visualtree.c > CMakeFiles\tp10.dir\visualtree.c.i

CMakeFiles/tp10.dir/visualtree.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/tp10.dir/visualtree.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\visualtree.c -o CMakeFiles\tp10.dir\visualtree.c.s

# Object files for target tp10
tp10_OBJECTS = \
"CMakeFiles/tp10.dir/avl.c.obj" \
"CMakeFiles/tp10.dir/bst.c.obj" \
"CMakeFiles/tp10.dir/tp10.c.obj" \
"CMakeFiles/tp10.dir/visualtree.c.obj"

# External object files for target tp10
tp10_EXTERNAL_OBJECTS =

tp10.exe: CMakeFiles/tp10.dir/avl.c.obj
tp10.exe: CMakeFiles/tp10.dir/bst.c.obj
tp10.exe: CMakeFiles/tp10.dir/tp10.c.obj
tp10.exe: CMakeFiles/tp10.dir/visualtree.c.obj
tp10.exe: CMakeFiles/tp10.dir/build.make
tp10.exe: CMakeFiles/tp10.dir/linklibs.rsp
tp10.exe: CMakeFiles/tp10.dir/objects1.rsp
tp10.exe: CMakeFiles/tp10.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking C executable tp10.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\tp10.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/tp10.dir/build: tp10.exe

.PHONY : CMakeFiles/tp10.dir/build

CMakeFiles/tp10.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\tp10.dir\cmake_clean.cmake
.PHONY : CMakeFiles/tp10.dir/clean

CMakeFiles/tp10.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\smani\Documents\GitHub\Cours\Algo\tp10 C:\Users\smani\Documents\GitHub\Cours\Algo\tp10 C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug C:\Users\smani\Documents\GitHub\Cours\Algo\tp10\cmake-build-debug\CMakeFiles\tp10.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/tp10.dir/depend


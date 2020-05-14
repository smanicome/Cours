cmake_minimum_required(VERSION 3.16)
project(tp9 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(tp9
        bst.c
        bst.h
        tp09.c
        tree.c
        tree.h
        visualtree.c
        visualtree.h)

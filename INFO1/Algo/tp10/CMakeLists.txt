cmake_minimum_required(VERSION 3.16)
project(tp10 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(tp10
        avl.c
        avl.h
        bst.c
        bst.h
        tp10.c
        visualtree.c
        visualtree.h)

cmake_minimum_required(VERSION  3.10)
project(MyCppProject)

set(CMAKE_CXX_STANDARD  14)

include_directories(include)

file(GLOB_RECURSE SOURCES "src/main/*.cpp")
add_executable(MyCppProject ${SOURCES})


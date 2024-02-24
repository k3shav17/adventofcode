#include <iostream>
#include <fstream>
#include <ostream>
#include <string>

int floor_level(std::string stairs)
{
    int floor = 0;
    for (int i = 0; i < stairs.size(); i++)
    {
        if (stairs[i] == '(')
        {
            floor++;
        }
        else
        {
            if (stairs[i] == ')')
            {
                floor--;
            }
        }
    }
    return floor;
}

int main()
{
    std::string filePath = "/Users/keshav/AdventOfCode/inputs/2015/day1.txt";
    std::ifstream file(filePath);

    if (!file.is_open())
    {
        std::cerr << "Unable to open the file, please check the path : " << filePath << std::endl;
        return 1;
    }

    std::string line;
    while (std::getline(file, line))
    {
      std::cout << floor_level(line) << std::endl;
    }

    file.close();
    return 0;
}

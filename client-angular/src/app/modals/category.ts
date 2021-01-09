

export class Category {
  id: number;
  name: string;
  subCategory : Category[];
  parentCategory : Category[];
  active: boolean;
}

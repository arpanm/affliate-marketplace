import dayjs from 'dayjs';

export interface IAiTool {
  id?: number;
  name?: string | null;
  description?: string | null;
  vendor?: string | null;
  linkUrl?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IAiTool> = {
  isActive: false,
};
